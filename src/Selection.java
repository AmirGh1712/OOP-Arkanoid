/**
 * The main class for selection.
 *
 * @author Amir Gheriani 212938724
 *
 * @param <T> the type of the return value.
 */
public class Selection<T> {
    private String key;
    private String message;
    private T returnValue;
    /**Constructor.
     *
     * @param key the key.
     * @param message the message.
     * @param returnValue the value.
     */
    public Selection(String key, String message, T returnValue) {
        this.key = key;
        this.message = message;
        this.returnValue = returnValue;
    }
    /**Return the key.
     *
     * @return the key.
     */
    public String getKey() {
        return this.key;
    }
    /**Return the message.
    *
    * @return the message.
    */
    public String getMessage() {
        return this.message;
    }
    /**Return the value.
    *
    * @return the value.
    */
    public T getReturnValue() {
        return this.returnValue;
    }
}
