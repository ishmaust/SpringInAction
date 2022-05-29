package user.ishmaust.SpringInAction.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placedAt;

    @NotBlank(message = "Name is required.")
    private String deliveryName;
    @NotBlank(message = "Address is required.")
    private String deliveryStreet;
    @NotBlank(message = "City is required.")
    private String deliveryCity;
    @NotBlank(message = "State is required.")
    private String deliveryState;
    @NotBlank(message = "Zip is required.")
    private String deliveryZip;
    @CreditCardNumber(message = "Not a valid credit card number.")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[1-2])(\\/)([1-9]\\d)$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco design) {
        tacos.add(design);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
}
