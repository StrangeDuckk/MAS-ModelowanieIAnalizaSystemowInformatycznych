import jdk.jfr.Experimental;

import java.util.ArrayList;
import java.util.List;

public class CV {
    private Candidate candidate;//1 candidate can have multiple cv's, 1 cv can have 1 candidate

    private static List<CV> Cvs = new ArrayList<>();
    private String cvNumber; //Name_Surname_number
    private List<String> education; //only names
    private List<String> experience;//short info

    // ================= konstruktor =======================
    public CV(String cvNumber, List<String> education, List<String> experience, Candidate candidate){
        setCvNumber(cvNumber);
        setEducation(education);
        setExperience(experience);

        //todo polaczenie przez metode
        this.candidate = candidate;
    }
    // ================= relacje =================


    // ================= gettery i settery ==============
    public void setCvNumber(String cvNumber) {
        if(cvNumber == null || cvNumber.isBlank()){
            throw new IllegalArgumentException("CvNumber cannot be null");
        }
        if(!cvNumber.matches("^[^@\\s]+_[^@\\s]+_[0-9]+$")){//Name_Surname_number
            throw new IllegalArgumentException("Invalid CvNumber format, expected: Name_Surname_Number");
        }

        this.cvNumber = cvNumber;
    }
    public String getCvNumber() {
        return cvNumber;
    }

    public void setEducation(List<String> education) {
        if(education == null || education.isEmpty()){
            throw new IllegalArgumentException("Education list cannot be empty");
        }
        for (String e: education)
            addEducation(e);
    }
    public void removeEducation(String e){
        if(e == null || e.isBlank()){
            throw new IllegalArgumentException("Cannot remove null or blank education");
        }
        if(!this.education.contains(e)){
            throw new IllegalArgumentException("Education list doesn't contains record");
        }
        this.education.remove(e);
    }
    private void addEducation(String e) {
        if(e == null || e.isBlank()){
            throw new IllegalArgumentException("Education cannot be null or blank");
        }
        if(this.education.contains(e)){
            throw new IllegalArgumentException("Education already contains this education record");
        }
        this.education.add(e);
    }
    public void setExperience(List<String> experience) {
        if(experience == null || experience.isEmpty()){
            throw new IllegalArgumentException("Experience list cannot be empty");
        }
        for (String e: experience)
            addExperience(e);
    }
    public void removeExperience(String e){
        if(e == null || e.isBlank()){
            throw new IllegalArgumentException("Cannot remove null or blank experience");
        }
        if(!this.experience.contains(e)){
            throw new IllegalArgumentException("Experience list doesn't contains record");
        }
        this.experience.remove(e);
    }

    private void addExperience(String e) {
        if(e == null || e.isBlank()){
            throw new IllegalArgumentException("Experience cannot be null or blank");
        }
        if(this.experience.contains(e)){
            throw new IllegalArgumentException("Experience already contains this education record");
        }
        this.experience.add(e);
    }
}
/*
6. Dla każdej asocjacji należy utworzyć metody w obu powiązanych klasach, które umożliwią:
todo 6.1. Pobranie powiązanego obiektu lub obiektów (getter). W przypadku kolekcji należy
zapewnić, że nie będzie ona modyfikowana poza klasą, podobnie jak w przypadku ekstensji
lub atrybutu powtarzalnego.
todo 6.2. Utworzenie nowego powiązania. Metoda ta powinna automatycznie ustawić referencję
zwrotną.
todo 6.3. Usunięcie istniejącego powiązania. Metoda ta powinna automatycznie usunąć referencję
zwrotną.
todo 6.4. Jeżeli istnieje metoda do zastąpienia istniejącego powiązania z na inny obiekt, należy
upewnić się, że obie referencje ze starego powiązania zostaną usunięte przed utworzeniem
nowej relacji.

9. Asocjacja kwalifikowana
todo 9.1. Umożliwia “szybki” dostęp do powiązanego obiektu za pomocą kwalifikatora. Najczęściej
jest nim wymagany i unikalny atrybut powiązanej klasy.
todo 9.2. Zamiast zbioru referencji do powiązanych obiektów należy zastosować mapę (słownik),
której kluczem jest kwalifikator a wartością referencja do powiązanego obiektu.
todo 9.3. Jeżeli zmiana atrybutu będącego kwalifikatorem jest możliwa, to należy automatycznie
uaktualnić powiązanie wykorzystujące ten kwalifikator.
 */