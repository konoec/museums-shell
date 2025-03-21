package pe.edu.utp.objetos;

public class MuseoVisita {
    private String fechaCorte; // AAAAMMDD
    private int anio; // AAAA
    private String idMes; // (01:12)
    private String mes; // (A:Z)
    private String ubigeo; // (01:25)
    private String departamento; // (A:Z)
    private String provincia; // (A:Z)
    private String distrito; // (A:Z)
    private String idMuseo; // (A:Z)
    private String nombreMuseo; // (A:Z)
    private String tipo; // (A:Z)
    private int visitantesAdulto; // (0:9999999)
    private int visitantesEstudiante; // (0:9999999)
    private int visitantesNino; // (0:9999999)
    private int visitantesAdultoMayor; // (0:9999999)
    private int visitantesMilitares; // (0:9999999)
    private int visitantesDiscapacitado; // (0:9999999)
    private int totalVisitantes; // (0:9999999)

    // Constructor
    public MuseoVisita(String fechaCorte, int anio, String idMes, String mes, String ubigeo,
                       String departamento, String provincia, String distrito, String idMuseo,
                       String nombreMuseo, String tipo, int visitantesAdulto, int visitantesEstudiante,
                       int visitantesNino, int visitantesAdultoMayor, int visitantesMilitares,
                       int visitantesDiscapacitado, int totalVisitantes) {
        this.fechaCorte = fechaCorte;
        this.anio = anio;
        this.idMes = idMes;
        this.mes = mes;
        this.ubigeo = ubigeo;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.idMuseo = idMuseo;
        this.nombreMuseo = nombreMuseo;
        this.tipo = tipo;
        this.visitantesAdulto = visitantesAdulto;
        this.visitantesEstudiante = visitantesEstudiante;
        this.visitantesNino = visitantesNino;
        this.visitantesAdultoMayor = visitantesAdultoMayor;
        this.visitantesMilitares = visitantesMilitares;
        this.visitantesDiscapacitado = visitantesDiscapacitado;
        this.totalVisitantes = totalVisitantes;
    }

    // Getters y Setters
    public String getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(String fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getIdMes() {
        return idMes;
    }

    public void setIdMes(String idMes) {
        this.idMes = idMes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getIdMuseo() {
        return idMuseo;
    }

    public void setIdMuseo(String idMuseo) {
        this.idMuseo = idMuseo;
    }

    public String getNombreMuseo() {
        return nombreMuseo;
    }

    public void setNombreMuseo(String nombreMuseo) {
        this.nombreMuseo = nombreMuseo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getVisitantesAdulto() {
        return visitantesAdulto;
    }

    public void setVisitantesAdulto(int visitantesAdulto) {
        this.visitantesAdulto = visitantesAdulto;
    }

    public int getVisitantesEstudiante() {
        return visitantesEstudiante;
    }

    public void setVisitantesEstudiante(int visitantesEstudiante) {
        this.visitantesEstudiante = visitantesEstudiante;
    }

    public int getVisitantesNino() {
        return visitantesNino;
    }

    public void setVisitantesNino(int visitantesNino) {
        this.visitantesNino = visitantesNino;
    }

    public int getVisitantesAdultoMayor() {
        return visitantesAdultoMayor;
    }

    public void setVisitantesAdultoMayor(int visitantesAdultoMayor) {
        this.visitantesAdultoMayor = visitantesAdultoMayor;
    }

    public int getVisitantesMilitares() {
        return visitantesMilitares;
    }

    public void setVisitantesMilitares(int visitantesMilitares) {
        this.visitantesMilitares = visitantesMilitares;
    }

    public int getVisitantesDiscapacitado() {
        return visitantesDiscapacitado;
    }

    public void setVisitantesDiscapacitado(int visitantesDiscapacitado) {
        this.visitantesDiscapacitado = visitantesDiscapacitado;
    }

    public int getTotalVisitantes() {
        return totalVisitantes;
    }

    public void setTotalVisitantes(int totalVisitantes) {
        this.totalVisitantes = totalVisitantes;
    }
}
