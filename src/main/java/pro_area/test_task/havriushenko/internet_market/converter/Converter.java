package pro_area.test_task.havriushenko.internet_market.converter;

public interface Converter<T, E> {
    E convertToDto(T t);

    T convertToModel(E e);
}
