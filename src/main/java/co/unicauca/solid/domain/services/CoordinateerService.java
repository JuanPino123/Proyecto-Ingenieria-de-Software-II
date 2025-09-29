/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.solid.domain.services;

import co.unicauca.solid.domain.access.EvaluationRepository;
import co.unicauca.solid.domain.access.DegreeWorkRepository;
import co.unicauca.domain.Evaluation;
import co.unicauca.domain.DegreeWork;
import co.unicauca.domain.DegreeWorkStatus;
import co.unicauca.solid.domain.access.FormatARepository;
import java.time.LocalDateTime;

import java.util.logging.Logger;

/**
 *
 * @author Royman
 */
public class CoordinateerService {

    private static final Logger logger = Logger.getLogger(CoordinateerService.class.getName());
    private EvaluationRepository evaluationrepository = new EvaluationRepository();
    private DegreeWorkRepository degreeworkrepository = new DegreeWorkRepository();
    public CoordinateerService() {
        this.formatARepo = new FormatARepository();
    }

    public void EvaluateDegreeWork(DegreeWork degreeWork, DegreeWorkStatus decision, String observations, int idCoordinateer) {

        //Guardar
        Evaluation eval = new Evaluation();
        eval.setQualification(decision.name());
        eval.setObservations(observations);
        eval.setCreateAt(LocalDateTime.now());
        eval.setIdDegreeWork(degreeWork.getId());
        eval.setIdCoordinateer(idCoordinateer);

        evaluationrepository.insert(eval);

        //Actualizar degreework
        degreeworkrepository.updateStatus(degreeWork.getId(), decision.name());

        // para simular el envio del correo
        logger.info("Correo enviado a docente" + degreeWork.getTeacher().getUser().getEmail()
                + " | estudiantes: " + degreeWork.getEmailStudents()
                + " | decision: " + decision
                + " | observaciones: " + observations);
    }
    private final FormatARepository formatARepo;

    /**
     * Evalúa un Formato A: lo aprueba o rechaza con observaciones.
     *
     * @param formatAId ID del formato A
     * @param status Estado de la evaluación (APROBADO o RECHAZADO)
     * @param observaciones Comentarios del coordinador
     */
    public void evaluateFormatA(int formatAId, DegreeWorkStatus status, String observaciones) {
        try {
            // 1. Actualizar en el repositorio (BD)
            formatARepo.updateEvaluation(formatAId, status, observaciones);

            // 2. Simulación de notificación (correo → logger)
            logger.info("Se notificó a docentes y estudiantes sobre la evaluación del Formato A "
                    + formatAId + ": " + status + " - " + observaciones);

        } catch (Exception e) {
            logger.severe("Error al evaluar Formato A " + formatAId + ": " + e.getMessage());
        }
    }

}
