package test.jdepend.framework;

import jdepend.framework.FileManager;

import java.io.File;
import java.io.IOException;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class FileManagerTest extends JDependTestCase {

    private FileManager fileManager;
    
    public FileManagerTest(String name) {
        super(name);
    }

    protected void setUp() {
        super.setUp();
        fileManager = new FileManager();
        fileManager.acceptInnerClasses(false);
    }

    protected void tearDown() {
        super.tearDown();
    }

    public void testEmptyFileManager() {
        assertEquals(0, fileManager.extractFiles().size());
    }
    
    public void testExtractFiles() throws Exception{
    	File f = File.createTempFile("bogus", ".jar", 
                new File(getTestDataDir()));
        fileManager.addDirectory(f.getPath());
        fileManager.addDirectory(f.getPath());
        assertEquals(1, fileManager.extractFiles().size());
        f.deleteOnExit();
    }

    public void testBuildDirectory() throws IOException {
        fileManager.addDirectory(getBuildDir());
        assertEquals(26, fileManager.extractFiles().size());
    }

    public void testNonExistentDirectory() {

        try {
            
            fileManager.addDirectory(getBuildDir() + "junk");
            fail("Non-existent directory: Should raise IOException");
        
        } catch (IOException expected) {
            assertTrue(true);
        }
    }

    public void testInvalidDirectory() {

        String file = getTestDir() + getPackageSubDir() + "ExampleTest.java";
        
        try {
            
            fileManager.addDirectory(file);
            fail("Invalid directory: Should raise IOException");
            
        } catch (IOException expected) {
            assertTrue(true);
        }
    }

    public void testClassFile() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() + "JDepend.class");

        assertTrue(new FileManager().acceptClassFile(f));
    }

    public void testNonExistentClassFile() {
        File f = new File(getBuildDir() + "JDepend.class");
        assertFalse(new FileManager().acceptClassFile(f));
    }

    public void testInvalidClassFile() {
        File f = new File(getHomeDir() + "build.xml");
        assertFalse(new FileManager().acceptClassFile(f));
    }

    public void testJar() throws IOException {
        File f = File.createTempFile("bogus", ".jar", 
            new File(getTestDataDir()));
        fileManager.addDirectory(f.getPath());
        assertTrue(fileManager.acceptJarFile(f));
        f.deleteOnExit();
    }

    public void testZip() throws IOException {
        File f = File.createTempFile("bogus", ".zip", 
            new File(getTestDataDir()));
        fileManager.addDirectory(f.getPath());
        assertTrue(fileManager.acceptJarFile(f));
        f.deleteOnExit();
    }

    public void testWar() throws IOException {
        File f = File.createTempFile("bogus", ".war", 
            new File(getTestDataDir()));
        fileManager.addDirectory(f.getPath());
        assertTrue(fileManager.acceptJarFile(f));
        f.deleteOnExit();
    }
    /**
     * @author icewariya
     */
    public void testAddDirectoryExample1() {
        try {
        	fileManager.addDirectory("invalid");
        }catch(IOException ex) {
        	assertEquals("Invalid directory or JAR file: invalid", ex.getMessage());
        	
        }
    }
    
    public void testacceptFileExample1() {
       File f = new File("invalid");
       
       assertFalse(fileManager.acceptFile(f));
    }
    
    public void testacceptFileExample2() {
    	 File f = new File(getBuildDir() + getPackageSubDir() + "JDepend.class");
        
        assertTrue(fileManager.acceptFile(f));
     }
    
    public void testacceptFileExample3() throws Exception{
    	File f = File.createTempFile("bogus", ".jar", 
                new File(getTestDataDir()));
        fileManager.addDirectory(f.getPath());
       assertTrue(fileManager.acceptFile(f));
       f.deleteOnExit();
    }
    
    public void testacceptClassFileExample1() {
        File f = new File("invalid");
        
        assertFalse(fileManager.acceptClassFile(f));
     }
     
     public void testacceptClassFileExample2() {
     	 File f = new File(getBuildDir() + getPackageSubDir() + "JDepend.class");
         
         assertTrue(fileManager.acceptClassFile(f));
      }
     
     public void testacceptClassFileNameExample1() {
         assertFalse(fileManager.acceptClassFileName("invalid"));
      }
     
     public void testacceptClassFileNameExample2() {
         assertFalse(fileManager.acceptClassFileName("ExampleConcreteClass$ExampleInnerClass.class"));
        
      }
     
     public void testacceptClassFileNameExample3() {
         assertFalse(fileManager.acceptClassFileName("ExampleConcrete.jar"));
        
      }
     
     public void testacceptClassFileNameExample4() {
         assertTrue(fileManager.acceptClassFileName("ExampleConcrete.class"));
        
      }
}