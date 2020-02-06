package com.example.demo;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * @author Abhimanyu Singh
 * @author abhimanyusingh@hackerrank.com
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderedTestRunner extends BlockJUnit4ClassRunner {

    public OrderedTestRunner(Class<?> klass) throws InitializationError {
		super(klass);
		// TODO Auto-generated constructor stub
	}

	/**
     * @param clazz test class
     * @throws InitializationError initialization error
     */
    

    /** @return test methods ordered by execution order */
    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        Map<FrameworkMethod, Integer> orders = new HashMap();

        List<FrameworkMethod> methods = super.computeTestMethods();

        int maxOrder = 0;

        for (FrameworkMethod method : methods) {
            Order order = method.getAnnotation(Order.class);
            maxOrder = Math.max(maxOrder, order == null ? 0 : order.value());

            orders.put(method, order == null ? null : order.value());
        }

        final int order = maxOrder + 1;

        methods.forEach(method -> orders.computeIfAbsent(method, value -> order));

        return methods.stream()
                .sorted((f1, f2) -> orders.get(f1) - orders.get(f2))
                .collect(toList());
    }
}