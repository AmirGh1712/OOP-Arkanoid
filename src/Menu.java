/**
 * The main interface for menu.
 *
 * @author Amir Gheriani 212938724
 *
 * @param <T> the type of the return value.
 */
public interface Menu<T> extends Animation {
    /**Add selection.
     *
     * @param key the key.
     * @param message the message.
     * @param returnVal the return value.
     */
    void addSelection(String key, String message, T returnVal);
    /**Add sub menu.
     *
     * @param key the key.
     * @param message the message.
     * @param subMenu the sub menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
    /**Get the status.
     *
     * @return the status.
     */
    T getStatus();
}