/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util;

/**
 *
 * @author Ferox
 */
public class QRCodeImages {
    
    private byte[] imageBytes;
    private String caption;
    private String path;
    
//    public QRCodeImages(byte[] imageBytes, String caption, String path) {
//        setCaption(caption);
//        setImageBytes(imageBytes);
//        setPath(path);
//    }

    /**
     * @return the imageBytes
     */
    public byte[] getImageBytes() {
        return imageBytes;
    }

    /**
     * @param imageBytes the imageBytes to set
     */
    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    /**
     * @return the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption the caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
}
