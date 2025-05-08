package ista.cursoM4A.controller;

import ista.cursoM4A.model.Registro;
import ista.cursoM4A.repository.RegistroRepository;
import ista.cursoM4A.service.AesEncryptionService;
import ista.cursoM4A.service.RsaEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final AesEncryptionService aesService;
    private final RsaEncryptionService rsaService;
    private final RegistroRepository registroRepository;

    @Autowired
    public CryptoController(AesEncryptionService aesService,
                            RsaEncryptionService rsaService,
                            RegistroRepository registroRepository) {
        this.aesService = aesService;
        this.rsaService = rsaService;
        this.registroRepository = registroRepository;
    }

    @PostMapping("/aes/encrypt")
    public String encryptAes(@RequestBody String input) throws Exception {
        String cifrado = aesService.encrypt(input);
        Registro reg = new Registro();
        reg.setOriginal(input);
        reg.setCifrado(cifrado);
        registroRepository.save(reg);
        return cifrado;
    }

    @PostMapping("/aes/decrypt")
    public String decryptAes(@RequestBody String input) throws Exception {
        return aesService.decrypt(input);
    }

    @PostMapping("/rsa/encrypt")
    public String encryptRsa(@RequestBody String input) throws Exception {
        return rsaService.encrypt(input);
    }

    @PostMapping("/rsa/decrypt")
    public String decryptRsa(@RequestBody String input) throws Exception {
        return rsaService.decrypt(input);
    }

    // (Opcional) Listar todos los registros
    @GetMapping("/registros")
    public java.util.List<Registro> listarRegistros() {
        return registroRepository.findAll();
    }
}
