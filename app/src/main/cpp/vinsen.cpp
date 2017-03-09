#include <jni.h>
#include <string>


#include "com_vinsen_application_VoiceHelper.h"

JNIEXPORT jstring JNICALL Java_com_vinsen_application_VoiceHelper_play
        (JNIEnv *env, jclass jcls, jstring path) {
    std::string hello = env->GetStringUTFChars(path, false);
    return env->NewStringUTF(hello.c_str());
}
