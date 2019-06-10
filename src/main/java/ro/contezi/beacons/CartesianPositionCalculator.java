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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class CartesianPositionCalculator implements PositionCalculator<CartesianPosition> {

    private final double acceptableError;

    CartesianPositionCalculator(double acceptableError) {
        this.acceptableError = acceptableError;
    }

    @Override
    public CartesianPosition getPosition(Map<Beacon<CartesianPosition>, Double> distances) {
        if (distances.size() < 3) {
            throw new IllegalArgumentException("Not enough beacons");
        }
        List<Map.Entry<Beacon<CartesianPosition>, Double>> twoBeacons =
                distances.entrySet().stream().limit(2).collect(Collectors.toList());
        List<CartesianPosition> positionsMatchingDistances = new CartesianTriangle().getPositionsMatchingDistances(
                twoBeacons.get(0).getKey().getPosition(), twoBeacons.get(1).getKey().getPosition(),
                twoBeacons.get(0).getValue(), twoBeacons.get(1).getValue());
        return positionsMatchingDistances.stream().filter(cartesianPosition ->
            distances.entrySet().stream().allMatch(entry ->
                    Math.abs(entry.getKey().getPosition().getDistanceFrom(cartesianPosition)
                            - entry.getValue()) <= acceptableError)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("No matching position"));
    }
}
