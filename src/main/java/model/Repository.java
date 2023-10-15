package model;
import exceptions.LogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
    public class Repository<T> {
        private List<T> objects = new ArrayList<>();

        public Repository() {
        }

        public T get(int id) {
            if (id >= 0 && id < size()) {
                return objects.get(id);
            }
            throw new LogicException("Wrong index of list!");
        }

        public void add(T element) {
            if (element == null) {
                throw new LogicException("Cannot add null!");
            }
            objects.add(element);
        }

        public T find(Predicate<T> predicate) {
            for (T element : objects) {
                if (predicate.test(element)) {
                    return element;
                }
            }
            return null;
        }

        public List<T> findAll(Predicate<T> predicate) {
            List<T> result = new ArrayList<>();
            for (T element : objects) {
                if (predicate.test(element)) {
                    result.add(element);
                }
            }
            return result;
        }

        public String report() {
            StringBuilder chain = new StringBuilder();
            for (T element : objects) {
                chain.append(element.toString()).append("\n");
            }
            return chain.toString();
        }

        public int size() {
            return objects.size();
        }

        public void remove(T element) {
            objects.remove(element);
        }
}
