/**
 * The main class for Countr.
 *
 * @author Amir Gheriani
 */
public class Counter {
    private int value;
    /**Constructor.
     */
    public Counter() {
        this.value = 0;
    }
    /**Add number to current count.
     *
     * @param number the number to add.
     */
    void increase(int number) {
       this.value += number;
    }
    /**Subtract number from current count.
    *
    * @param number the number to subtract.
    */
    void decrease(int number) {
        this.value -= number;
     }
    /**Get current count.
     *
     * @return the current count.
     */
    int getValue() {
        return this.value;
    }
}