import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements  Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;

    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket){
        try{
            this.socket= socket;
           this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
           this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           this.clientUsername= bufferedReader.readLine();
           clientHandlers.add(this);//  this is client handler objects;
            broadcastMessage("Server: "+clientUsername+" has entered the chat!");



        }
        catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }


    @Override
    public void run() {// whatever we do here is run on a seperate thread
        String messageFromClient;
        while(socket.isConnected()){
            try{ messageFromClient= bufferedReader.readLine();
            broadcastMessage(messageFromClient);
            }catch(IOException e){
                closeEverything(socket, bufferedReader,bufferedWriter);
                break;
            }
        }

    }
    public void broadcastMessage(String messaggeToSend){
        for(ClientHandler clientHandler:clientHandlers){
            try{
                if(!clientHandler.clientUsername.equals(clientUsername)){
                    clientHandler.bufferedWriter.write(messaggeToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();

                }

            }
            catch(IOException e){
                closeEverything(socket, bufferedReader,bufferedWriter);
            }
        }

    }
    public void removeClientHandler(){
        clientHandlers.remove((this));
        broadcastMessage( "Server"+clientUsername+"has left the chat!");

    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHandler();
        try{
             if(bufferedReader!=null){
                 bufferedReader.close();


             }
             if(bufferedWriter!=null){
                 bufferedWriter.close();
             }
             if(socket!=null){}{
                 socket.close();

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }
}
