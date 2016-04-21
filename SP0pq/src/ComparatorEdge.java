import java.util.Comparator;

public class ComparatorEdge implements Comparator<Edge>{

	@Override
	public int compare(Edge arg0, Edge arg1) {
		
		return arg1.Weight - arg0.Weight;
	}

}
