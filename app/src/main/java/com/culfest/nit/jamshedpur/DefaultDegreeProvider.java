package com.culfest.nit.jamshedpur;

/**
 * Created by nit on 24-Jan-16.
 */
public class DefaultDegreeProvider implements IDegreeProvier {
    public float[] getDegrees(int count, float totalDegrees) {
        if (count < 1) {
            return new float[]{};
        }

        float[] result = null;
        result = new float[count];
        float delta = totalDegrees / 7;

        for (int index = 0; index < count; index++) {
            int tmpIndex = index;
            result[index] = tmpIndex * delta;
        }
        return result;
    }
}
