package me.theyinspire.projects.infotheory.encdec.bit;

public enum Bit {

    ZERO(0), ONE(1);

    private final int value;

    Bit(final int value) {

        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public Bit multiply(Bit that) {
        return this == ZERO || that == ZERO ? ZERO : ONE;
    }

    public Bit add(Bit that) {
        return this == that ? ZERO : ONE;
    }

}
