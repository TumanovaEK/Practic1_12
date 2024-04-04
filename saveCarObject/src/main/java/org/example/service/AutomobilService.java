package org.example.service;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class AutomobilService {
    public static class Automobil implements Externalizable {
        public String brand;
        public String model;
        public int year;
        public double price;
        public boolean isNew;

        public Automobil() {
            // Пустой конструктор
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(brand);
            out.writeObject(model);
            out.writeInt(year);
            out.writeDouble(price);
            out.writeBoolean(isNew);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            brand = (String) in.readObject();
            model = (String) in.readObject();
            year = in.readInt();
            price = in.readDouble();
            isNew = in.readBoolean();
        }
        @Override
        public String toString() {
            return "Марка: " + brand + "\n" +
                    "Модель: " + model + "\n" +
                    "Год выпуска: " + year + "\n" +
                    "Цена: " + price + "\n" +
                    "Новый: " + isNew;
        }

        public String getBrand() {
            return brand;
        }

        public String getModel() {
            return model;
        }

        public int getYear() {
            return year;
        }

        public double getPrice() {
            return price;
        }

        public boolean isNew() {
            return isNew;
        }
    }

}