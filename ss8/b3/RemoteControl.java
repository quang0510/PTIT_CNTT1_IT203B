package b3;

public class RemoteControl {
    private Command currentCommand;
    private Command undoCommand;

    public void setCommand(Command command) {
        this.currentCommand = command;
    }

    public void pressButton() {
        currentCommand.execute();
        undoCommand = currentCommand;
    }

    public void pressUndo() {
        if (undoCommand != null) {
            System.out.print("Undo: ");
            undoCommand.undo();
        }
    }
}
