package org.aos.logparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;



public class StreamTest
{
	public static <K> void main (String [] args)  {
		class Foo {
			String a;
			String b;
			Foo (String a, String b)  {
				this.a = a;
				this.b = b;
			}
			@Override
			public String toString() {
				return "Foo [a=" + a + ", b=" + b + "]";
			}
			
		}
		
		Function<Foo, List<Foo>> ToList = new Function<Foo, List<Foo>>()
		{
			@Override
			public List<Foo> apply(Foo foo)
			{
				List <Foo> list = new ArrayList<Foo>();
				list.add(foo);
				return list;
			}
		};
		
		
		
		Map<String, List<Foo>> outputMap;
		
		Foo w = new Foo ("a", "1");
		Foo x = new Foo ("b", "2");
		Foo y = new Foo ("c", "1");
		Foo z = new Foo ("d", "2");
		
		List<Foo> foos = new ArrayList <Foo> ();
		foos.add(w);
		foos.add(x);
		foos.add(y);
		foos.add(z);
		
//		outputMap = foos.stream().collect(Collectors.toMap(entry->entry.b, Function.identity()));
//		outputMap = foos.stream().collect(Collectors.toMap(entry->entry.b, ToList(Function.identity())));
		outputMap = foos.stream().map(
				s ->  {
					List <Foo> list = new ArrayList<Foo>();
					list.add(s);
					return list;
				})
				.collect(Collectors.toMap(s -> s.get(0).b, Function.identity(),
				(s,a) ->  {
					s.addAll(a);
					return s;					
				}));
		for (String key : outputMap.keySet())  {
			System.out.println(key +" -> " + outputMap.get(key).toString());
		}
		

		
		
		
	}
	

}


// 			if(_providerService.find(task.getProviderName()).getInsightsVersion() == 2)
