#include <jni.h>
#include <string>
#include <cmath>
using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_com_ktynnz_scavengerhuntproject_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_ktynnz_scavengerhuntproject_MainActivity_randomQuestion(JNIEnv *env, jobject thiz,
                                                                 jint num1, jint num2, jint ans) {
    string check;
    if (num1 + num2 == ans)
    {
        check = "You are correct.";
    }
    else
    {
        check = "Please try again.";
    }

    string correctAnswer = check;
    return env->NewStringUTF(correctAnswer.c_str());

}