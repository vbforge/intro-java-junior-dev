package com.vbforge.oop.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

@Disabled
class Task02_ShapeTest {

    @Test void circle() {
        Task02_Shape c = new Task02_Shape.Circle(2.0);
        assertThat(c.name()).isEqualTo("Circle");
        assertThat(c.area()).isCloseTo(Math.PI * 4, within(1e-9));
        assertThat(c.perimeter()).isCloseTo(2 * Math.PI * 2, within(1e-9));
    }

    @Test void rectangle() {
        Task02_Shape r = new Task02_Shape.Rectangle(3, 4);
        assertThat(r.name()).isEqualTo("Rectangle");
        assertThat(r.area()).isEqualTo(12.0);
        assertThat(r.perimeter()).isEqualTo(14.0);
    }

    @Test void triangle() {
        Task02_Shape t = new Task02_Shape.Triangle(3, 4, 5);   // right triangle
        assertThat(t.name()).isEqualTo("Triangle");
        assertThat(t.area()).isCloseTo(6.0, within(1e-9));
        assertThat(t.perimeter()).isCloseTo(12.0, within(1e-9));
    }

    @Test void invalidDimensionsRejected() {
        assertThatThrownBy(() -> new Task02_Shape.Circle(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Task02_Shape.Rectangle(-1, 2))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Task02_Shape.Triangle(1, 1, 10))  // triangle inequality
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test void describeFormat() {
        assertThat(new Task02_Shape.Rectangle(3, 4).describe())
                .isEqualTo("Rectangle: area=12.00, perimeter=14.00");
    }

    @Test void totalAreaAndLargest() {
        Task02_Shape[] shapes = {
                new Task02_Shape.Rectangle(1, 2),
                new Task02_Shape.Circle(3),
                new Task02_Shape.Triangle(3, 4, 5)
        };
        double total = Task02_Shape.totalArea(shapes);
        assertThat(total).isCloseTo(2.0 + Math.PI * 9 + 6.0, within(1e-9));
        assertThat(Task02_Shape.largest(shapes)).isInstanceOf(Task02_Shape.Circle.class);
    }

    @Test void emptyArrayBehavior() {
        assertThat(Task02_Shape.totalArea(new Task02_Shape[0])).isZero();
        assertThatThrownBy(() -> Task02_Shape.largest(new Task02_Shape[0]))
                .isInstanceOf(IllegalArgumentException.class);
    }
}