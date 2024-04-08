
    public class Profile{
        private String name;
        private String lastName;
        private int birthDate;

        public Profile(String name, String lastName, int birthDate) {
            this.name = name;
            this.lastName = lastName;
            this.birthDate = birthDate;
        }

        // Getters y setters
        public String getName() {
            return name;
        }

        public String getLastName() {
            return lastName;
        }

        public int getBirthDate() {
            return birthDate;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setBirthDate(int birthDate) {
            this.birthDate = birthDate;
        }
    }
