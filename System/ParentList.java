public class ParentList {
    private static ParentList parentList;
    private static ParentRecord parentRecord;

    private ParentRecord() {
        parentList = new ParentList();
    }

    public static ParentRecord getInstance() {
        if (parentRecord == null) {
            parentRecord = new ParentRecord();
        }
        return parentRecord;
    }

    public void addParent(Parent parent) {
        parentList.addParent(parent);
    }

    public Parent getParent(Parent parent) {
        return parentList.getParent(parent);
    }

    public void editParent(Parent parent) {
        parentList.editParent(parent);
    }

    public void saveParent(Parent parent) {
        parentList.saveParent(parent);
    }
}