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

import static ro.contezi.beacons.CartesianPositionAssert.assertCloseTo;

import java.util.List;
import org.junit.Test;

public class CartesianTriangleTest {
    private CartesianPosition a = new CartesianPosition(0, 0);
    private CartesianPosition b = new CartesianPosition(1, 0);
    private CartesianTriangle cartesianTriangle = new CartesianTriangle();

    @Test
    public void findsEquilateralTriangles() throws Exception {
        double distanceCToA = 1;
        double distanceCToB = 1;

        List<CartesianPosition> positionsMatchingDistances = cartesianTriangle
                .getPositionsMatchingDistances(a, b, distanceCToA, distanceCToB);

        assertCloseTo(new CartesianPosition(.5, Math.sqrt(3) / 2), positionsMatchingDistances.get(0));
        assertCloseTo(new CartesianPosition(.5, -Math.sqrt(3) / 2), positionsMatchingDistances.get(1));
    }

    @Test
    public void findsRightAngleTriangles() throws Exception {
        double distanceCToA = 1;
        double distanceCToB = Math.sqrt(2);

        List<CartesianPosition> positionsMatchingDistances = cartesianTriangle
                .getPositionsMatchingDistances(a, b, distanceCToA, distanceCToB);

        assertCloseTo(new CartesianPosition(0, 1), positionsMatchingDistances.get(0));
        assertCloseTo(new CartesianPosition(0, -1), positionsMatchingDistances.get(1));
    }

    @Test
    public void findsDegenerateTriangles() throws Exception {
        double distanceCToA = 1;
        double distanceCToB = 2;

        List<CartesianPosition> positionsMatchingDistances = cartesianTriangle
                .getPositionsMatchingDistances(a, b, distanceCToA, distanceCToB);

        assertCloseTo(new CartesianPosition(-1, 0), positionsMatchingDistances.get(0));
        assertCloseTo(new CartesianPosition(-1, 0), positionsMatchingDistances.get(1));
    }

}