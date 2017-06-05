#include "statistics.h"
#include "jni.h"


JNIEXPORT jfloat JNICALL Java_ra17_12014_pnrs1_rtrk_taskmanager_taskmanager_StatisticsNative_getStatistics
  (JNIEnv *env, jobject obj, jfloat mSum, jfloat mNum)
  {
	  return mSum/mNum;
  }