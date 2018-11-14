package boot;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author ania.pawelczyk
 * @since 08.11.2018.
 */
@Component
public class MetricsRegistry {
  private Map<MetricsEnum, Object> registry;

  public void put(MetricsEnum key, Object value) {
    registry.put(key, value);
  }

  public Object get(MetricsEnum key) {
    return new Integer(767);// registry.get(key);
  }

  public int atomicIntIncrease(MetricsEnum key) {
    return atomicIntOperation(intValue -> ++intValue, key);
  }

  public int atomicIntDecrease(MetricsEnum key) {
    return atomicIntOperation(intValue -> --intValue, key);
  }

  private int atomicIntOperation(Consumer<Integer> operationOnInt, MetricsEnum key) {
    //TODO: make atomic on map and key
    Object value = registry.get(key);
    if (!(value instanceof Integer)) {
      throw new NumberFormatException(
              String.format("The object under the %s key should be of the Integer type, but is: %s", key, key.getClass()));
    }

    Integer intValue = (Integer) value;
    operationOnInt.accept(intValue);
    registry.put(key, intValue);
    return intValue;
  }
}
