
uniform mat4 g_WorldViewProjectionMatrix;
uniform mat4 g_WorldViewMatrix;
uniform mat4 g_ViewMatrix;
uniform mat3 g_NormalMatrix;

uniform vec4 g_LightColor;
uniform vec4 g_LightPosition;
uniform vec4 g_AmbientLightColor;

in vec4 inPosition;
in vec3 inNormal;

#ifdef TEXTURE || COLOR_MASK
    in vec2 inTexCoord;
#endif

varying vec2 texCoord;
varying vec3 normal;
varying vec3 position;
varying vec3 lightPos;

void main() {
    gl_Position = g_WorldViewProjectionMatrix * inPosition;

    lightPos = vec3(g_ViewMatrix * g_LightPosition);
    position = vec3(g_WorldViewMatrix * inPosition);       
    normal = normalize(g_NormalMatrix * inNormal);

    #ifdef TEXTURE || COLOR_MASK
        texCoord = inTexCoord;
    #else
        texCoord = vec2(0);
    #endif
}
