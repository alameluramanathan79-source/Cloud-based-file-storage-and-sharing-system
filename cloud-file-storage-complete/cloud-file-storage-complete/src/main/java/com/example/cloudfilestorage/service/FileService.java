package com.example.cloudfilestorage.service;
import com.example.cloudfilestorage.model.*; import com.example.cloudfilestorage.repository.*;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import org.springframework.web.multipart.MultipartFile;
import javax.crypto.Cipher; import javax.crypto.spec.*; import java.nio.file.*; import java.security.SecureRandom; import java.util.*; import java.time.LocalDateTime;
@Service public class FileService {
 final StoredFileRepository fr; final UserRepository ur; final Path dir; final byte[] key; final SecureRandom random=new SecureRandom();
 public FileService(StoredFileRepository f,UserRepository u,@Value("${file.storage.location}")String d,@Value("${file.encryption.key}")String k){fr=f;ur=u;dir=Paths.get(d).toAbsolutePath();key=Base64.getDecoder().decode(k);if(key.length!=32)throw new IllegalStateException("Encryption key must be 32 bytes");}
 public void save(MultipartFile mf,String username)throws Exception{
  if(mf.isEmpty())throw new IllegalArgumentException("Choose a file."); User u=ur.findByUsername(username).orElseThrow();
  String n=UUID.randomUUID()+".enc";byte[] iv=new byte[12];random.nextBytes(iv);Cipher c=Cipher.getInstance("AES/GCM/NoPadding");c.init(Cipher.ENCRYPT_MODE,new SecretKeySpec(key,"AES"),new GCMParameterSpec(128,iv));
  byte[] enc=c.doFinal(mf.getBytes());byte[] all=new byte[iv.length+enc.length];System.arraycopy(iv,0,all,0,iv.length);System.arraycopy(enc,0,all,iv.length,enc.length);
  Files.createDirectories(dir);Files.write(dir.resolve(n),all);
  fr.save(new StoredFile(Paths.get(Optional.ofNullable(mf.getOriginalFilename()).orElse("file")).getFileName().toString(),n,mf.getContentType(),mf.getSize(),LocalDateTime.now(),u));
 }
 public List<StoredFile> list(String u){return fr.findByOwnerUsernameOrderByUploadedAtDesc(u);}
 public StoredFile find(long id,String u){return fr.findByIdAndOwnerUsername(id,u).orElseThrow();}
 public byte[] read(StoredFile f)throws Exception{byte[] all=Files.readAllBytes(dir.resolve(f.getStoredName()));byte[] iv=Arrays.copyOfRange(all,0,12);byte[] enc=Arrays.copyOfRange(all,12,all.length);Cipher c=Cipher.getInstance("AES/GCM/NoPadding");c.init(Cipher.DECRYPT_MODE,new SecretKeySpec(key,"AES"),new GCMParameterSpec(128,iv));return c.doFinal(enc);}
 public void delete(StoredFile f)throws Exception{Files.deleteIfExists(dir.resolve(f.getStoredName()));fr.delete(f);}
}