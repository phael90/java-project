package models;

public enum Category {

    VEHICLES("Vehicles"),
    ELECTRONICS("Electronics"),
    HOUSEHOLDITEM("Household Items"),
    CLOTHES("Clothes"),
    SERVICES("Services"),
    PROPERTY("Property"),
    ANIMALS("Animals"),
    MISCELLANEOUS("Miscellaneous");

    private String niceName;

    Category(String niceName) {

        this.niceName = niceName;
    }

    public String getNiceName() {

        return niceName;
    }
}
