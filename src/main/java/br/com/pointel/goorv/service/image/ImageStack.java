package br.com.pointel.goorv.service.image;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import br.com.pointel.goorv.service.wizard.WizImage;

public class ImageStack implements ClipboardOwner {

    private final List<BufferedImage> images = new ArrayList<>();

    private BufferedImage discardOnTryToStack = null;

    public void clear() {
        try {
            discardOnTryToStack = getFromClipboard();
        } catch (Exception e) {
        }
        images.clear();
    }

    public void stackFromClipboard() throws Exception {
        BufferedImage image = getFromClipboard();
        images.add(image);
    }

    public boolean tryToStackFromClipboardIfNew() throws Exception {
        try {
            var image = getFromClipboard();
            if ((discardOnTryToStack == null || !WizImage.isSame(image, discardOnTryToStack))
                    && (getSize() == 0 || !WizImage.isSame(image, images.get(getSize() - 1)))) {
                images.add(image);
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public BufferedImage getFromClipboard() throws Exception {
        Transferable content = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (content == null) {
            throw new Exception("Error: Nothing found in clipboard");
        }
        if (!content.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            throw new Exception("Error: No image found in clipboard");
        }
        return (BufferedImage) content.getTransferData(DataFlavor.imageFlavor);
    }

    public void composeToClipboard(String alignment) throws Exception {
        int maxWidth = 0;
        int totalHeight = 0;
        for (var image : images) {
            if (image.getWidth() > maxWidth) {
                maxWidth = image.getWidth();
            }
            totalHeight += image.getHeight();
        }
        var composed = new BufferedImage(maxWidth, totalHeight, BufferedImage.TYPE_INT_ARGB);
        composed.getGraphics().setColor(Color.WHITE);
        composed.getGraphics().fillRect(0, 0, maxWidth, totalHeight);
        var top = 0;
        for (var image : images) {
            var x = 0;
            if ("Center".equals(alignment)) {
                x = maxWidth / 2 - image.getWidth() / 2;
            } else if ("Right".equals(alignment)) {
                x = maxWidth - image.getWidth();
            }
            composed.getGraphics().drawImage(image, x, top, null);
            top += image.getHeight();
        }
        discardOnTryToStack = composed;
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new ImageTransfer(composed), this);
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
    }

    public int getSize() {
        return images.size();
    }

}
