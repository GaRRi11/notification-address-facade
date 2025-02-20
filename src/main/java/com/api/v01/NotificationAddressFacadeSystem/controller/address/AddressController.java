package com.api.v01.NotificationAddressFacadeSystem.controller.address;

import com.api.v01.NotificationAddressFacadeSystem.data.Address.Address;
import com.api.v01.NotificationAddressFacadeSystem.data.Address.AddressDTO;
import com.api.v01.NotificationAddressFacadeSystem.data.Address.AddressTextUpdateDTO;
import com.api.v01.NotificationAddressFacadeSystem.exceptions.CustomException;
import com.api.v01.NotificationAddressFacadeSystem.service.AddressService.AddressService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Display all addresses in Thymeleaf view
    @GetMapping("/addresses")
    public String viewAddresses(Model model) {
        model.addAttribute("addresses", addressService.getAllAddresses());
        return "address"; // Thymeleaf view name
    }

    // Display the address creation form
    @GetMapping("/create")
    public String showCreateAddressForm(Model model) {
        model.addAttribute("addressDTO", new AddressDTO()); // Create a new AddressDTO for the form
        return "create-address"; // This would be the view where you render the form
    }

    // Handle the form submission for creating an address
    @PostMapping("/create")
    public String createAddress(@Valid AddressDTO addressDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Invalid input data.");
            return "create-address"; // Return to the same page to show errors
        }

        if (addressService.checkIfAddressExists(addressDTO.getCustomerId(), addressDTO.getAddressTypeId(), addressDTO.getText())) {
            model.addAttribute("error", "Address already exists.");
            return "create-address"; // Return to the same page with an error message
        }

        // Proceed to create the address
        addressService.createAddress(addressDTO.fromDTO(addressDTO));
        model.addAttribute("success", "Address created successfully!");
        return "redirect:/address/addresses"; // Redirect to the address listing page
    }


    // Handle address deletion
    @PostMapping("/delete/{id}")
    public String deleteAddress(@PathVariable @Min(value = 1, message = "ID must be a positive number") Long id, Model model) {
        if (!addressService.existsById(id)) {
            model.addAttribute("error", "Address with id: " + id + " does not exist.");
            return "address"; // Return with error
        }

        addressService.delete(id);
        model.addAttribute("success", "Address deleted successfully!");
        return "redirect:/api/address/addresses"; // Redirect to the address listing page
    }

    // Handle address text update
    @PostMapping("/update-address")
    public String updateAddress(@Valid AddressTextUpdateDTO addressTextUpdateDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Invalid input data.");
            return "address"; // Return to the same page to show errors
        }

        if (!addressService.existsById(addressTextUpdateDTO.getAddressId())) {
            model.addAttribute("error", "Address with id: " + addressTextUpdateDTO.getAddressId() + " does not exist.");
            return "address"; // Return with error
        }

        addressService.updateAddressText(addressTextUpdateDTO.getAddressId(), addressTextUpdateDTO.getText());
        model.addAttribute("success", "Address updated successfully!");
        return "redirect:/api/address/addresses"; // Redirect to the address listing page
    }
}
