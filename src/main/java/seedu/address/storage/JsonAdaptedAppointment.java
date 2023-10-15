package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {

    private final String description;
    private final LocalDateTime dateTime;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("description") String description, @JsonProperty("dateTime") String dateTime) {
        this.description = description;
        this.dateTime = LocalDateTime.parse(dateTime);
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        description = source.getDescription();
        dateTime = source.getDateTime();
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("dateTime")
    public String getDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        return new Appointment(description, dateTime);
    }
}
