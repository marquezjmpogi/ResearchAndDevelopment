package researchanddevelopment

import java.awt.image.BufferedImage
import java.io.*;
import java.util.*;

class ResearchAndDevController {

    def index() { 
        redirect(action : 'evtUploadfile', controller: 'ResearchAndDev')
    }
    def evtUploadfile(){
        
    }
    def createDirectory(){
        
        println("================== createDirectory ===================")
        println("params: "+params)
        // =================== to create directory =========================
        File newDir = new File('./web-app/imageContainer')
        if(!newDir.exists()){
           //if not directory not exist create new directory
           newDir.mkdirs() 
        }else{
            println("may directory na ")
        }
        
        try {
            
            def mhsr = request.getFile('photo')
            def fileName = mhsr.originalFilename
            //if(!mhsr?.empty && mhsr.size < 1024*2000){
            if(!mhsr?.empty){
                mhsr.transferTo( new File("./web-app/imageContainer/${fileName}.jpg") )
            }
            
        }catch(FileNotFoundException fnfe){
            println("============= FileNotFoundException =============")
            println("ERROR: "+fnfe.printStackTrace())
            render "path is not available"
        } catch(MissingPropertyException mpe){
            println("============= MissingPropertyException =============")
            println("ERROR: "+mpe.printStackTrace())
            render "path is not available"
        }
    }
    
    // function to view image on a specific directory
    // already tested in tomcat
    def renderImage = {

        String profileImagePath = "./web-app/imageContainer/photo.jpg"
        //String profileImagePath = grailsApplication.grails.profile.images.path
        File file = new File(profileImagePath)
        response.outputStream << file.newInputStream()
        response.outputStream.flush()

    }
    def download(long id) { //download a file saved inside the file system
        /*FSDocument documentInstance = FSDocument.get(id)
        if ( documentInstance == null) {
            flash.message = “Document not found.”
            redirect (action:’index’)
        } else {
            response.setContentType(“APPLICATION/OCTET-STREAM”)
            response.setHeader(“Content-Disposition”, “Attachment;Filename=\”${documentInstance.fileName}\””)

            def file = new File(documentInstance.fullPath)
            def fileInputStream = new FileInputStream(file)
            def outputStream = response.getOutputStream()

            byte[] buffer = new byte[4096];
            int len;
            while ((len = fileInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }

            outputStream.flush()
            outputStream.close()
            fileInputStream.close()
        }*/
    }
    def delete(long id) {

        /*FSDocument FSDocumentInstance = FSDocument.get(id)
        if (FSDocumentInstance == null) {
        return
        }

        File file = new File(FSDocumentInstance.fullPath)
        file.delete()
        FSDocumentInstance.delete(flush: true)
        redirect(action: “index”)*/
    }
    
}
