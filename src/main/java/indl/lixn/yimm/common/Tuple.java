package indl.lixn.yimm.common;

/**
 * @author lixn
 * @description
 * @date 2023/01/06 10:30
 **/
public final class Tuple<left, right> {

    private final left left;
    private final right right;

    public Tuple(left left, right right) {
        this.left = left;
        this.right = right;
    }

    public left getLeft() {
        return left;
    }

    public right getRight() {
        return right;
    }
}
