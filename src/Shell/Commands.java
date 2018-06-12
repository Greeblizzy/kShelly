package Shell;

public enum Commands implements Descriptor<String> {
    MAN {
        @Override
        public String toString() {
            return Constants.MAN;
        }

        @Override
        public String getDescription() {
            return Constants.MAN_DESC;
        }
    },
}
