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
import static org.assertj.core.api.Assertions.assertThat;
import static ro.contezi.beacons.CartesianPositionAssert.assertCloseTo;

import org.assertj.core.data.Offset;
import org.junit.Test;

public class CartesianPositionTest {
    private CartesianPosition origin = new CartesianPosition(0, 0);

    @Test
    public void distanceOneFrom10() throws Exception {
        assertThat(origin.getDistanceFrom(new CartesianPosition(1, 0))).isCloseTo(1, Offset.offset(0.01));
    }

    @Test
    public void distanceOneFrom01() throws Exception {
        assertThat(origin.getDistanceFrom(new CartesianPosition(0, 1))).isCloseTo(1, Offset.offset(0.01));
    }

    @Test
    public void distanceSqrt2From11() throws Exception {
        assertThat(origin.getDistanceFrom(new CartesianPosition(1, 1))).isCloseTo(1.41, Offset.offset(0.01));
    }

    @Test
    public void slopeOfHorizontalForwardIs0() throws Exception {
        assertThat(origin.getSlopeTo(new CartesianPosition(1, 0))).isCloseTo(0, Offset.offset(0.01));
    }

    @Test
    public void withSlope0ToHorizontalForward() throws Exception {
        assertCloseTo(new CartesianPosition(1, 0), origin.getWithSlopeAndDistance(0, 1));
    }

    @Test
    public void slopeTo11IsPIOver4() throws Exception {
        assertThat(origin.getSlopeTo(new CartesianPosition(1, 1))).isCloseTo(PI / 4, Offset.offset(0.01));
    }

    @Test
    public void withSlopePIOver4To11() throws Exception {
        assertCloseTo(new CartesianPosition(1, 1), origin.getWithSlopeAndDistance(PI / 4, Math.sqrt(2)));
    }

    @Test
    public void slopeToCloseTo01IsPIOver2() throws Exception {
        assertThat(origin.getSlopeTo(new CartesianPosition(0.01, 1))).isCloseTo(PI / 2, Offset.offset(0.01));
    }

    @Test
    public void withSlopeCloseToPIOver2To01() throws Exception {
        assertCloseTo(new CartesianPosition(0, 1), origin.getWithSlopeAndDistance(PI / 2 - 0.01, 1));
    }

    @Test
    public void slopeTo01IsPIOver2() throws Exception {
        assertThat(origin.getSlopeTo(new CartesianPosition(0, 1))).isCloseTo(PI / 2, Offset.offset(0.01));
    }

    @Test
    public void withSlopeToPIOver2To01() throws Exception {
        assertCloseTo(new CartesianPosition(0, 1), origin.getWithSlopeAndDistance(PI / 2, 1));
    }

    @Test
    public void slopeToMinusCloseTo01IsPIOver2() throws Exception {
        assertThat(origin.getSlopeTo(new CartesianPosition(-0.01, 1))).isCloseTo(PI / 2, Offset.offset(0.01));
    }

    @Test
    public void withSlopeMinusCloseToPIOver2To01() throws Exception {
        assertCloseTo(new CartesianPosition(0, 1), origin.getWithSlopeAndDistance(PI / 2 + 0.01, 1));
    }

    @Test
    public void slopeToMinus11Is3PIOver4() throws Exception {
        assertThat(origin.getSlopeTo(new CartesianPosition(-1, 1))).isCloseTo(3 * PI / 4, Offset.offset(0.01));
    }

    @Test
    public void withSlope3PIOver4ToMinus11() throws Exception {
        assertCloseTo(new CartesianPosition(-1, 1), origin.getWithSlopeAndDistance(3 * PI / 4, Math.sqrt(2)));
    }

    @Test
    public void slopeToMinus10IsPI() throws Exception {
        assertThat(origin.getSlopeTo(new CartesianPosition(-1, 0))).isCloseTo(PI, Offset.offset(0.01));
    }

    @Test
    public void withSlopePIToMinus10() throws Exception {
        assertCloseTo(new CartesianPosition(-1, 0), origin.getWithSlopeAndDistance(PI, 1));
    }

    @Test
    public void slopeToMinus1Minus1Is5PIOver4() throws Exception {
        assertThat(origin.getSlopeTo(new CartesianPosition(-1, -1))).isCloseTo(5 * PI / 4, Offset.offset(0.01));
    }

    @Test
    public void withSlope5PIOver4ToMinus1Minus1() throws Exception {
        assertCloseTo(new CartesianPosition(-1, -1), origin.getWithSlopeAndDistance(5 * PI / 4, Math.sqrt(2)));
    }

    @Test
    public void slopeToMinusCloseTo0Minus1Is3PIOver2() throws Exception {
        assertThat(origin.getSlopeTo(new CartesianPosition(-0.01, -1))).isCloseTo(3 * PI / 2, Offset.offset(0.01));
    }

    @Test
    public void slopeTo0Minus1Is3PIOver2() throws Exception {
        assertThat(origin.getSlopeTo(new CartesianPosition(0, -1))).isCloseTo(3 * PI / 2, Offset.offset(0.01));
    }

    @Test
    public void withSlope3PIOver2To0Minus1() throws Exception {
        assertCloseTo(new CartesianPosition(0, -1), origin.getWithSlopeAndDistance(3 * PI / 2, 1));
    }

    @Test
    public void slopeToCloseTo0Minus1Is3PIOver2() throws Exception {
        assertThat(origin.getSlopeTo(new CartesianPosition(0.01, -1))).isCloseTo(3 * PI / 2, Offset.offset(0.01));
    }

    @Test
    public void slopeTo1Minus1Is7PIOver4() throws Exception {
        assertThat(origin.getSlopeTo(new CartesianPosition(1, -1))).isCloseTo(7 * PI / 4, Offset.offset(0.01));
    }

    @Test
    public void withSlope7PIOver4To1Minus1() throws Exception {
        assertCloseTo(new CartesianPosition(1, -1), origin.getWithSlopeAndDistance(7 * PI / 4, Math.sqrt(2)));
    }

    @Test
    public void slopeTo1MinusCloseTo0Is2PI() throws Exception {
        assertThat(origin.getSlopeTo(new CartesianPosition(1, -0.01))).isCloseTo(2 * PI, Offset.offset(0.01));
    }

    @Test
    public void withSlope0ToHorizontalForward2PI() throws Exception {
        assertCloseTo(new CartesianPosition(1, 0), origin.getWithSlopeAndDistance(2 * PI, 1));
    }

}