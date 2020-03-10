package pl.netigen.legacy.utils;


public class PhysicsEngine {
    private static float referencedVelocity;

    public static float smoothMove(float current, float target, float smoothTime, float maxSpeed, float deltaTime) {
        smoothTime = Math.max(0.0001f, smoothTime);
        float num = 2f / smoothTime;
        float num2 = num * deltaTime;
        float num3 = 1f / (1f + num2 + 0.48f * num2 * num2 + 0.235f * num2 * num2 * num2);
        float num4 = current - target;
        float num5 = target;
        float num6 = maxSpeed * smoothTime;
        if (num4 <= -num6) {
            num4 = -num6;
        } else if (num4 >= num6) {
            num4 = num6;
        }
        target = current - num4;
        float num7 = (referencedVelocity + num * num4) * deltaTime;
        referencedVelocity = (referencedVelocity - num * num7) * num3;
        float num8 = target + (num4 + num7) * num3;
        if (num5 - current > 0f == num8 > num5) {
            num8 = num5;
            referencedVelocity = (num8 - num5) / deltaTime;
        }
        return num8;
    }
}
