/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fingerprint.system.ProjectFoldersContainer.ProjectFolders;
import fingerprint.system.ConfigFilesContainer.ConfigFiles;
import java.io.File;
import java.nio.file.Paths;
import java.util.Dictionary;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author stephane.kibonge
 */
public class ProjectFolderConfigTest {
    
    public ProjectFolderConfigTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void FolderCreationTest() {
        Dictionary folders = ProjectFolders.makeProjectFolders_byName("Test");
        
        assertEquals(true, new File((String) folders.get("project")).isDirectory());
        assertEquals(true, new File((String) folders.get("data")).isDirectory());
        assertEquals(true, new File((String) folders.get("config")).isDirectory());
        assertEquals(true, new File((String) folders.get("documents")).isDirectory());
    }
    
    @Test
    public void ConfigFilesTest() {
        Dictionary folders = ProjectFolders.makeProjectFolders_byName("Test");
        String configFilePath = Paths.get((String)folders.get("config"), "config.properties").toString();
        
        Dictionary configDefaults = ConfigFiles.getDefaults();
        configDefaults.put("testProperty", "testValue");
        ConfigFiles.writeFile(configFilePath, configDefaults);
        Dictionary configData = ConfigFiles.readFile(configFilePath);

        assertEquals(configDefaults, configData);
    }
}
