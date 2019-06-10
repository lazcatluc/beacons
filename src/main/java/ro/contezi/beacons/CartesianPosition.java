/*
Copyright 2019 Catalin Lazar

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package ro.contezi.beacons;

import static java.lang.Math.PI;

class CartesianPosition implements Position<CartesianPosition> {
    private final double x;
    private final double y;

    CartesianPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getDistanceFrom(CartesianPosition other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    double getSlopeTo(CartesianPosition other) {
        double realSlope = getRealSlope(other);
        if (realSlope < 0) {
            return 2 * PI + realSlope;
        }
        return realSlope;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    CartesianPosition getWithSlopeAndDistance(double slope, double distance) {
        double dx = distance * Math.cos(slope);
        double dy = distance * Math.sin(slope);
        return new CartesianPosition(x + dx, y + dy);
    }

    private double getRealSlope(CartesianPosition other) {
        if (x > other.x) {
            return PI + other.getRealSlope(this);
        }
        if (x == other.x) {
            if (y < other.y) {
                return PI / 2;
            }
            return - PI / 2;
        }

        return Math.atan((y - other.y) / (x - other.x));
    }

    @Override
    public String toString() {
        return "CartesianPosition{" + "x=" + x +
                ", y=" + y +
                '}';
    }
}
