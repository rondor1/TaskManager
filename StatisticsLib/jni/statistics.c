#include "statistics.h"
#include <jni.h>

JNIEXPORT jfloat JNICALL Java_ra17_12014_pnrs1_rtrk_taskmanager_taskmanager_StatisticsNative_getStatisticsResult
  (JNIEnv *env, jobject obj, jfloat mDone, jfloat mTotal)
  {
		return (jfloat) (100* (mDone/mTotal));
  }