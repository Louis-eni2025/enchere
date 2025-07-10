document.addEventListener("DOMContentLoaded", function () {
    //When i check #achats all the checkboxes inside #vente__sousOptions are disabled
    const achatsCheckbox = document.getElementById("achats");
    const venteCheckbox = document.getElementById("ventes");
    const venteSousOptionsCheckboxes = document.querySelectorAll(".ventes__sous-option__checkbox");
    const achatsSousOptionsCheckboxes = document.querySelectorAll(".achats__sous-option__checkbox");

    achatsCheckbox.addEventListener("change", function () {
        if (achatsCheckbox.checked) {
            venteCheckbox.checked = false;
        }
        venteSousOptionsCheckboxes.forEach(function (checkbox) {
            checkbox.disabled = achatsCheckbox.checked;
            checkbox.checked = !achatsCheckbox.checked;
        });
        achatsSousOptionsCheckboxes.forEach(function (checkbox) {
            checkbox.disabled = !achatsCheckbox.checked;
        });
    })

    //When i check #vente all the checkboxes inside #achats__sousOptions are disabled
    venteCheckbox.addEventListener("change", function () {
        if (venteCheckbox.checked) {
            achatsCheckbox.checked = false;
        }
        achatsSousOptionsCheckboxes.forEach(function (checkbox) {
            checkbox.disabled = venteCheckbox.checked;
            checkbox.checked = !venteCheckbox.checked;
        });
        venteSousOptionsCheckboxes.forEach(function (checkbox) {
            checkbox.disabled = !venteCheckbox.checked;
        });
    })

    venteSousOptionsCheckboxes.forEach(function (checkbox) {
        checkbox.addEventListener("change", function () {
            if (checkbox.checked) {
                manageUniqueChoice(checkbox, venteSousOptionsCheckboxes);
            }
        });
    })

    achatsSousOptionsCheckboxes.forEach(function (checkbox) {
        checkbox.addEventListener("change", function () {
            if (checkbox.checked) {
                manageUniqueChoice(checkbox, achatsSousOptionsCheckboxes);
            }
        });
    })


    function manageUniqueChoice(checkedCheckbox, checkboxes) {
        checkboxes.forEach(function (checkbox) {
            if (checkbox !== checkedCheckbox) {
                checkbox.checked = false;
            }
        });
    }
})