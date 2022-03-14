public interface Storage {

    default int converse(Capacity conv, int storageCap) {
        if (conv == Capacity.MEGABYTE)
            return storageCap * 1024;
        if (conv == Capacity.KYLOBYTE)
            return storageCap * 1024 * 1024;
        if (conv == Capacity.BYTE)
            return storageCap * 1024 * 1024 * 1024;
        return storageCap;
    }
}
