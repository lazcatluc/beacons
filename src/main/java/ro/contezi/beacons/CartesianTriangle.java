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

import java.util.Arrays;
import java.util.List;

/**
 * Using the law of cosines: a^2 = b^2 + c^2 - 2*b*c*cos A
 * We can rewrite it as cos A = (b^2 + c^2 - a^) / (2 * b * c)
 * We will determine angle A up to a minus sign because cos is symmetric
 * Then we can determine the slopes and cartesian positions
 * <p>
 * https://en.wikipedia.org/wiki/Law_of_cosines
 */
class CartesianTriangle {
    List<CartesianPosition> getPositionsMatchingDistances(CartesianPosition a, CartesianPosition b,
                                                          double distanceCToA, double distanceCToB) {
        double distanceAToB = a.getDistanceFrom(b);
        double cosA = (distanceCToA * distanceCToA + distanceAToB * distanceAToB - distanceCToB * distanceCToB) /
                (2 * distanceCToA * distanceAToB);
        double aRadians = Math.acos(cosA);

        double slopeAToB = a.getSlopeTo(b);
        double slopeAToCUp = slopeAToB + aRadians;
        double slopeAToCDown = slopeAToB - aRadians;
        return Arrays.asList(a.getWithSlopeAndDistance(slopeAToCUp, distanceCToA),
                a.getWithSlopeAndDistance(slopeAToCDown, distanceCToA));
    }
}
