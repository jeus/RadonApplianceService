/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jeus
 * Created: Oct 16, 2016
 */
ALTER TABLE `logs` ADD COLUMN `toler` INT(11) NULL DEFAULT NULL COMMENT 'tolerance of radon' AFTER `radon`;

