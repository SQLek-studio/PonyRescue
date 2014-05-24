
uniform vec4 g_LightColor;
uniform vec4 g_LightPosition;
uniform vec4 g_AmbientLightColor;

varying vec2 texCoord;
varying vec3 normal;
varying vec3 position;
varying vec3 lightPos;

#ifdef TEXTURE
  uniform sampler2D Texture;
#endif
#ifdef COLOR_MASK
  uniform sampler2D ColorMask;
#endif

void main(){

    vec3 testLamp = vec3(1,1,0);
    vec4 outColor = vec4(1.0);

    #ifdef TEXTURE
        outColor = texture2D(Texture,texCoord);
    #endif

    vec3 lightVec = normalize(g_LightPosition.xyz - lightPos);

    gl_FragColor = outColor;
    gl_FragColor.x = dot(testLamp,normal);
}