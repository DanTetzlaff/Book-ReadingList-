package stacks;

public class TimeAllStackOperations 
{
	public static void main(String[] args) 
	{
		StackTimer timer = new StackTimer();
		timer.addStack( new ArrayStack<Integer>() );
		timer.addStack( new ArrayListStack<Integer>() );
		timer.addStack( new VectorStack<Integer>() );
		timer.addStack( new LinkListStack<Integer>() );
		timer.timeAllOperations();
	}
}
