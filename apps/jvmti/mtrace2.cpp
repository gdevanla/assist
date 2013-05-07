/*
	Name: mtrace2.cpp
	Copyright:
	Author: GV
	Date: 27/02/13 06:46
	Description:
*/

#include <stdio.h>
#include <string.h>
#include "jvmti.h"
#include <string>
#include <iostream>
using namespace std;
static FILE* out;
static char* packagePattern;

//Start tracing only when "Main" method in the Java file is invoked.
//Set to true in methodentry, set to false in methodexit
static bool startMethodRecord;

/**
 * Every JVMTI interface returns an error code, which should be checked to avoid
 * any cascading errors down the line. The function GetErrorName() returns the
 * actual enumeration constant name, making the error messages much easier to
 * understand.
 */
static void check_jvmti_error(jvmtiEnv *jvmti, jvmtiError error, const char *msg) {
    if (error != JVMTI_ERROR_NONE) {
        char *name = NULL;
        (jvmti)->GetErrorName( error, &name);
        fprintf(stderr, "ERROR: JVMTI: %d(%s): %s\n", error, (name == NULL ? "Unknown" : name), (msg == NULL ? "" : msg));
    }
}

/**
 * Only a single MethodEntry callback may be active at any one time.
 */
static jrawMonitorID callback_lock;

static void enter_critical_section(jvmtiEnv *jvmti, jrawMonitorID lock) {
    jvmtiError error = (jvmti)->RawMonitorEnter( lock);
    check_jvmti_error(jvmti, error, "Cannot enter with raw monitor");
}

static void exit_critical_section(jvmtiEnv *jvmti, jrawMonitorID lock) {
    jvmtiError error = (jvmti)->RawMonitorExit( lock);
    check_jvmti_error(jvmti, error, "Cannot exit with raw monitor");
}

static const int CALL_CHAIN_LENGTH = 1;

/* Callback for JVMTI_Method Entry */
void JNICALL method_entry_callback(jvmtiEnv *jvmti, JNIEnv* jni, jthread thread, jmethodID mid) {
    jvmtiError error;
    int depth;
    jclass declaring_class; /* Unmanaged JNI object reference! */

    enter_critical_section(jvmti, callback_lock);



    for (depth = 0; depth < CALL_CHAIN_LENGTH; depth++) {
        char *method_name, *method_signature, *class_signature;
        char **const SKIP_GENERIC = NULL;

        jmethodID method;
        jlocation location;

        if ((error = (jvmti)->GetFrameLocation( thread, depth, &method, &location)) == JVMTI_ERROR_NO_MORE_FRAMES) {
            //fputs("ROOT\n", out);
            break;
        }
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't get frame location\n");

        error = (jvmti)->GetMethodName( method, &method_name, &method_signature, SKIP_GENERIC);
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't get method name\n");

        error = (jvmti)->GetMethodDeclaringClass( method, &declaring_class);
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't get declaring class\n");

        error = (jvmti)->GetClassSignature( declaring_class, &class_signature, SKIP_GENERIC);
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't get class signature\n");

        (jni)->DeleteLocalRef( declaring_class);


        //setMethodRecord is set to True, once out of main Method, set to false.
        if(strcmp(method_name,"main")==0)
        {
        	startMethodRecord = true;
        }


        /*if(startMethodRecord)
        {
        	//cout<<class_signature<<"."<<method_name<<"("<<method_signature<<")"<<endl;
        	size_t num = strlen("Lcom/estjni");
        	//cout<<"Comparing:"<<class_signature<<":"<<strncmp(class_signature,"Lcom/testjni",num)<<endl;
        }*/


        //size_t num = strlen("Lcom/testjni");
        if(startMethodRecord )
        {
        //cout<<class_signature<<"."<<method_name<<"("<<method_signature<<")"<<endl;
        //fputs("CALL\t", out);
        std::string s1(class_signature);
        std::string s2(method_signature);
        s1.erase(0,1);
        s2.erase(s2.size()-2,2);
        fputs(s1.c_str() , out);
        fputs(" : ", out);
        fputs(method_name, out);
        //fputc('(', out);
        fputs(s2.c_str(), out);
        fputc(')',out);

        if (depth != CALL_CHAIN_LENGTH - 1)
            fputc('\t', out);
        else
            fputc('\n', out);
        }

        error = (jvmti)->Deallocate( (unsigned char*) method_name);
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't deallocate memory\n");
        error = (jvmti)->Deallocate( (unsigned char*) method_signature);
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't deallocate memory\n");
        error = (jvmti)->Deallocate( (unsigned char*) class_signature);
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't deallocate memory\n");
    }

    exit_critical_section(jvmti, callback_lock);
}



/* Callback for JVMTI_Method Exit */
static void JNICALL method_exit_callback(jvmtiEnv *jvmti,JNIEnv* jni,
     jthread thread,
     jmethodID method,
     jboolean was_popped_by_exception,
     jvalue return_value)
{
	jvmtiError error;
    int depth;
    jclass declaring_class; /* Unmanaged JNI object reference! */

    enter_critical_section(jvmti, callback_lock);



    for (depth = 0; depth < CALL_CHAIN_LENGTH; depth++) {
        char *method_name, *method_signature, *class_signature;
        char **const SKIP_GENERIC = NULL;

        jmethodID method;
        jlocation location;

        if ((error = (jvmti)->GetFrameLocation( thread, depth, &method, &location)) == JVMTI_ERROR_NO_MORE_FRAMES) {
            //fputs("ROOT\n", out);
            break;
        }
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't get frame location\n");

        error = (jvmti)->GetMethodName( method, &method_name, &method_signature, SKIP_GENERIC);
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't get method name\n");

        error = (jvmti)->GetMethodDeclaringClass( method, &declaring_class);
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't get declaring class\n");

        error = (jvmti)->GetClassSignature( declaring_class, &class_signature, SKIP_GENERIC);
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't get class signature\n");

        (jni)->DeleteLocalRef( declaring_class);

        if(strcmp(method_name,"main")==0)
        {
        	startMethodRecord = false;
        }

        //size_t num = strlen("Lcom/testjni");

        /*Uncomment the following block to record the Method Exits.*/
        //if(startMethodRecord && strncmp(class_signature,"Lcom/testjni",num)==0) {
//        fputs("Returned\t", out);
//
//        fputs(class_signature, out);
//        fputs(" : ", out);
//        fputs(method_name, out);
//        fputc('\(', out);
//        fputs(method_signature, out);
//        fputc(')',out);
//
//        if (depth != CALL_CHAIN_LENGTH - 1)
//            fputc('\t', out);
//        else
//            fputc('\n', out);
//        }

        error = (jvmti)->Deallocate( (unsigned char*) method_name);
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't deallocate memory\n");
        error = (jvmti)->Deallocate( (unsigned char*) method_signature);
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't deallocate memory\n");
        error = (jvmti)->Deallocate( (unsigned char*) class_signature);
        check_jvmti_error(jvmti, error, "ERROR\tCouldn't deallocate memory\n");
    }

    exit_critical_section(jvmti, callback_lock);
}

void JNICALL vm_death_callback(jvmtiEnv *jvmti, JNIEnv* jni) {
    /* All MethodEntry callbacks should be finished by now; otherwise, we get a JVMTI_ERROR_WRONG_PHASE. */
    /* Wait 1000 milliseconds to give them plenty time to finish */
    jvmtiError err = (jvmti)->RawMonitorWait( callback_lock, 1000);
}



JNIEXPORT jint JNICALL Agent_OnLoad(JavaVM *jvm, char *options, void *reserved) {
    jvmtiEnv *jvmti = NULL;
    jvmtiCapabilities capabilities;
    jvmtiError error;
    jvmtiEventCallbacks callbacks;

    //set it to false to start recording methods from "main()"
    startMethodRecord = false;

    memset(&callbacks, 0, sizeof(callbacks));
    callbacks.MethodEntry = &method_entry_callback;
    callbacks.MethodExit = &method_exit_callback;
    callbacks.VMDeath = &vm_death_callback;



    if (options == NULL) {
        fprintf(stderr, "This agent requires the following option: <file>.\n\n"
            "For instance, the following command will cause the agent output write the trace into <file>:\n"
            "java -javapath:./tracer.so=<file>\n\n");
        return JNI_ERR;
    }

    out = fopen(options, "w");
    if (out == NULL) {
        fprintf(stderr, "Couldn't open file %s for writing", options);
        return JNI_ERR;
    }

    if ((jvm)->GetEnv( (void **) &jvmti, JVMTI_VERSION) != JNI_OK) {
        fprintf(stderr, "Couldn't get JVMTI environment");
        return JNI_ERR;
    }

    error = (jvmti)->CreateRawMonitor( "Tracer callbacks", &callback_lock);
    check_jvmti_error(jvmti, error, "Cannot create raw monitor");

    error = (jvmti)->GetCapabilities( &capabilities);
    check_jvmti_error(jvmti, error, "Couldn't get capabilities");

    capabilities.can_generate_method_entry_events = 1;
    capabilities.can_generate_method_exit_events = 1;

    error = (jvmti)->AddCapabilities( &capabilities);
    check_jvmti_error(jvmti, error, "Couldn't add capabilities");

    error = (jvmti)->SetEventCallbacks( &callbacks, sizeof(callbacks));
    check_jvmti_error(jvmti, error, "Couldn't set event callbacks");

    error = (jvmti)->SetEventNotificationMode( JVMTI_ENABLE, JVMTI_EVENT_METHOD_ENTRY, NULL);
    check_jvmti_error(jvmti, error, "Couldn't enable notification on method entry");

    error = (jvmti)->SetEventNotificationMode( JVMTI_ENABLE, JVMTI_EVENT_METHOD_EXIT, NULL);
    check_jvmti_error(jvmti, error, "Couldn't enable notification on method entry");

    error = (jvmti)->SetEventNotificationMode( JVMTI_ENABLE, JVMTI_EVENT_VM_DEATH, NULL);
    check_jvmti_error(jvmti, error, "Couldn't enable notification on VM death");

    return JNI_OK;
}

JNIEXPORT void JNICALL Agent_OnUnload(JavaVM *vm) {
    /* Can't hurt. */
    fflush(out);
    fclose(out);
}
int main(int argc, char** argv) {
return 0;
}
