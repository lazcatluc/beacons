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

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;

public class CartesianPositionCalculatorTest {
    private CartesianPositionCalculator cartesianPositionCalculator = new CartesianPositionCalculator(0.01);

    @Test
    public void findsCenterOfIsocelesTriangle() throws Exception {
        CartesianBeacon a = new CartesianBeacon(new CartesianPosition(0, 0));
        CartesianBeacon b = new CartesianBeacon(new CartesianPosition(1, 0));
        CartesianBeacon c = new CartesianBeacon(new CartesianPosition(.5, 1 + Math.sqrt(3) / 2));

        Map<Beacon<CartesianPosition>, Double> distances = new LinkedHashMap<>();
        distances.put(a, 1.0);
        distances.put(b, 1.0);
        distances.put(c, 1.0);

        CartesianPosition triangleCenter = cartesianPositionCalculator.getPosition(distances);

        assertCloseTo(new CartesianPosition(.5, Math.sqrt(3) / 2), triangleCenter);
    }

}