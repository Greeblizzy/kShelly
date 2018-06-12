package World;

import Shell.Constants;
import Shell.Descriptor;

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
    }, // TODO: fill in the rest of the commands
}
