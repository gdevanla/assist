#
# Sample GNU Makefile for building JVMTI Demo versionCheck
#
#  Example uses:
#       gnumake J2SDK=<java_home> OSNAME=solaris [OPT=true] [LIBARCH=sparc]
#       gnumake J2SDK=<java_home> OSNAME=solaris [OPT=true] [LIBARCH=sparcv9]
#       gnumake J2SDK=<java_home> OSNAME=linux   [OPT=true]
#       gnumake J2SDK=<java_home> OSNAME=win32   [OPT=true]
#
########################################################################

# Source lists
#LIBNAME=versionCheck
#SOURCES=versionCheck.c

LIBNAME=mtrace2
SOURCES=mtrace2.c


# Linux GNU C Compiler
 # GNU Compiler options needed to build it
	  COMMON_FLAGS=-fno-strict-aliasing -fPIC -fno-omit-frame-pointer
    # Options that help find errors
    COMMON_FLAGS+= -W -Wall  -Wno-unused -Wno-parentheses
    ifeq ($(OPT), true)
        CFLAGS=-O2 $(COMMON_FLAGS)
    else
        CFLAGS=-g $(COMMON_FLAGS)
    endif
    # Object files needed to create library
    OBJECTS=$(SOURCES:%.c=%.o)
    # Library name and options needed to build it
    LIBRARY=lib$(LIBNAME).dylib
    LDFLAGS=-dynamiclib -Wl,-headerpad_max_install_names,-undefined,dynamic_lookup,-compatibility_version,1.0,-current_version,1.0 -o $(LIBRARY) $(OBJ)
    # Libraries we are dependent on
    LIBRARIES=-lc
    # Building a shared library
    LINK_SHARED=$(LINK.c) -shared -o $@

# Common -I options
CFLAGS += -I.
CFLAGS += -I$(J2SDK)/include -I$(J2SDK)/include/$(OSNAME)

# Default rule
all: $(LIBRARY)

# Build native library
$(LIBRARY): $(OBJECTS)
	$(LINK_SHARED) $(OBJECTS) $(LIBRARIES)

# Cleanup the built bits
clean:
	rm -f $(LIBRARY) $(OBJECTS)

# Simple tester
test:
LD_LIBRARY_PATH=`pwd` $(J2SDK)/bin/java -agentlib:$(LIBNAME) -version
