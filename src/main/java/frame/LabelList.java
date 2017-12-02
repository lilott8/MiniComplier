package frame;

/**
 * @created: 12/1/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class LabelList {

    public Label head;
    public LabelList tail;

    public LabelList(Label head, LabelList tail) {
        this.head = head;
        this.tail = tail;
    }
}
